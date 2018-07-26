#!/bin/bash

# © Copyright 2017 Rede S.A.
#############################################################
# Nome     : post-install.sh
# Descrição: Script de install usado pelo RPM
# Autor    : Cléovon Santos
# Data     : 13/04/2018
# Empresa  : Rede

# SHES0009: Utilizar os comandos "unset –f unalias" e "unalias –a"
unset -f unalias
unalias -a

STR_USER="ec_appl"
#A variável armazenará uma string com o usuário da aplicação

STR_GROUP="Sistemas"
#A variável armazenará uma string com o grupo do usuário da aplicação

SIGLA="EC"
#A variável armazenará uma string com a sigla do sistema

getent group $STR_GROUP > /dev/null || groupadd -r $STR_GROUP
getent passwd $STR_USER > /dev/null || useradd -r -g $STR_GROUP -s /sbin/nologin -d /opt/$SIGLA -c "$STR_USER User" $STR_USER
chown -R $STR_USER.$STR_GROUP /opt/EC/erede/rebatedor-fe

mkdir /opt/EC/erede/rebatedor-fe/log
chown -R $STR_USER.$STR_GROUP /opt/EC/erede/rebatedor-fe/log
chmod 755 /opt/EC/erede/rebatedor-fe/log

mv /opt/EC/erede/rebatedor-fe/*.service /etc/systemd/system
chmod 664 /etc/systemd/system/rebatedor-fe.service

systemctl daemon-reload
