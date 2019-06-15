1. ubuntu server
2.  apt-get install postgresql
3.  sudo -u postgres psql
4.  create user fky with password 'fky';
5.  create database fmanager owner fky;
6.  grant all privileges on database fmanager to fky;      // give fky all permission of database fmanager