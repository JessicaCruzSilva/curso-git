#!/bin/bash

# © Copyright 2017 Rede S.A.
#############################################################
# Nome     : before-install.sh
# Descrição: Script de install usado pelo RPM
# Autor    : Cléovon Santos
# Data     : 13/04/2018
# Empresa  : Rede

# SHES0009: Utilizar os comandos “unset –f unalias” e “unalias –a”
unset -f unalias
unalias -a

STR_SERVICE_PATH=/etc/systemd/system/rebatedor-fe.service
#A variável armazenará o caminho do arquivo de serviço

STR_PACKAGE_NAME=`rpm -qa|grep rebatedor-fe`
#A variável receberá uma string retorno de uma checagem se o package esta instalado ou a removerá.

STR_USER="ec_appl"
#A variável armazenará uma string com o usuário da aplicação

STR_GROUP="Sistemas"
#A variável armazenará uma string com o grupo do usuário da aplicação

if [[ -f $STR_SERVICE_PATH ]]; then
  systemctl stop rebatedor-fe
  echo "stopped process and perform to install"
else
  echo "Install RPM"
fi
