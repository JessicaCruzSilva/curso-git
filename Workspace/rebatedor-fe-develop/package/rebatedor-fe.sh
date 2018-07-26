#!/bin/bash

# © Copyright 2017 Rede S.A.
#############################################################
# Nome     : rebatedor-fe.sh
# Descrição: Script de install usado pelo RPM
# Autor    : Cléovon Santos
# Data     : 13/04/2018
# Empresa  : Rede

### DEAMON HEADER
# Provides:       comunicacao-service
# Required-Start:    $local_fs $remote_fs $network $syslog
# Required-Stop:     $local_fs $remote_fs $network $syslog
# Default-Start:     3
# Default-Stop:      0 1 6
# Short-Description: starts the EC rebatedor-fe
# Description:       starts EC rebatedor-fe start-stop-daemon
### END INIT INFO

# SHES0009: Utilizar os comandos “unset –f unalias” e “unalias –a”
unset -f unalias
unalias -a

STR_BASE_PATH=/opt/EC/erede
#A variável armazenará uma string com o caminho da aplicação

STR_JARFILE_PATH=$STR_BASE_PATH/rebatedor-fe/bin/rebatedor-fe.jar
#A variável armazenará uma string com o caminho do jar da aplicação

STR_PROPERTIES_PATH=$STR_BASE_PATH/rebatedor-fe/conf/application.properties
#A variável armazenará uma string com o arquivo de configuração

STR_JVM_ARGS="-server -Xms256m -Xmx512m -XX:PermSize=128M -XX:MaxPermSize=256M"
#A variável armazenará uma string com os parametros de JVM

STR_PID_FILE=$STR_BASE_PATH/rebatedor-fe/rebatedor-fe.pid
#A variável armazenará uma string com o caminho do pid do serviço

STR_PID="-1"
#A variável armazenará uma string com o PID

STR_NOPID="-1"
#A variável armazenará uma string sem o PID

# Descrição: Retorna o PID da função
# Parâmetros: -
# Retornos: pid id
function print_process {
  echo $(<"$STR_PID_FILE")
}

# Descrição: Checa o PID do processo
# Parâmetros: -
# Retornos: verdadeiro ou falso
function check_pid_file {
  if [ -f $STR_PID_FILE ]
  then
    STR_PID=$(print_process)
    return 0;
  else
    STR_PID="-1"
    return 1;
  fi
}

# Descrição: Checa se processo esta rodando
# Parâmetros: -
# Retornos: verdadeiro ou falso
function check_pid_running {
  check_pid_file
  if [ "$STR_PID" == "$STR_NOPID" ]
  then
    return 1
  fi
  if ps -p $STR_PID > /dev/null
  then
    return 0
  else
    return 1
  fi
}

case "$1" in

status)
  if check_pid_running
  then
    echo "Process is running (" $STR_PID ")"
  else
    echo "Process not running"
  fi
;;

stop)

if check_pid_running
then
  kill -TERM $STR_PID
  echo -ne "Stopping Process"
  NOT_KILLED=1
  for i in {1..30}; do
    if check_pid_running
    then
      echo -ne "."
      sleep 1
    else
      NOT_KILLED=0
    fi
  done
  echo
  if [ $NOT_KILLED = 1 ]
  then
    echo "Cannot kill process " $STR_PID
    exit 1
  fi
  echo "Process stopped"
else
   echo "Process already stopped"
fi
;;

start)
  if check_pid_running
  then
    echo "Process already running"
    exit 1
  fi
  nohup java $STR_JVM_ARGS -jar $STR_JARFILE_PATH --spring.config.location=$STR_PROPERTIES_PATH > /dev/null 2>&1 &
  echo "Process started"
;;

restart)
  $0 stop
  if [ $? = 1 ]
  then
    exit 1
  fi
  $0 start
;;

*)
  echo "Usage: $0 {start|stop|restart|status}"
  exit 1

esac

exit 0
