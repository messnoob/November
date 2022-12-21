#!/bin/bash
cd "`dirname $0`"
echo "decompress mysql package..."
                tar zxvf mysql-5.7.24-linux-glibc2.12-x86_64.tar.gz
                echo "create mysql group && user"
                groupadd mysql
                useradd -g mysql mysql
                echo "prepare directories..."
                mkdir -p /data/mysql/mysqldata/binlog
                mkdir -p /data/mysql/mysqldata/innodb_log
                mkdir -p /data/mysql/mysqldata/innodb_ts
                mkdir -p /data/mysql/mysqldata/log
                mkdir -p /data/mysql/mysqldata/mydata
                mkdir -p /data/mysql/mysqldata/relaylog
                mkdir -p /data/mysql/mysqldata/sock
                mkdir -p /data/mysql/mysqldata/tmpdir
                chown -R mysql.mysql /data
                echo "copy mysql base && config file..."
                mv mysql-5.7.24-linux-glibc2.12-x86_64 /usr/local/mysql
                mv /etc/my.cnf /etc/my.cnf_`date +%Y%m%d%H%M%S`
                cp my.cnf /etc/my.cnf
                chown -R mysql.mysql /usr/local/mysql
                chown -R mysql.mysql /etc/my.cnf
                echo "add mysql path..."
                echo "PATH=\$PATH:/usr/local/mysql/bin" >> /etc/profile
                source /etc/profile
                echo "initialize mysql data..."
                /usr/local/mysql/bin/mysqld --defaults-file=/etc/my.cnf --initialize-insecure
                echo "create mysql service..."
                cat > /usr/lib/systemd/system/mysql.service <<EOFLVSD
[Unit]
Description=mysql
After=network.target

[Service]
Type=forking
ExecStart=/usr/local/mysql/bin/mysqld --defaults-file=/etc/my.cnf --daemonize
ExecStop=/usr/local/mysql/bin/mysqladmin -uroot -proot shutdown
PrivateTmp=true
Restart=always

[Install]
WantedBy=multi-user.target
EOFLVSD
                systemctl daemon-reload
                systemctl enable mysql
                systemctl start mysql
                echo "link mysql sock..."
                ln -s /data/mysql/mysqldata/sock/mysql.sock /tmp/mysql.sock
                echo "change password for root..."
                /usr/local/mysql/bin/mysql -e "alter user root@localhost identified by 'root'"
                echo "create schema for application..."
                /usr/local/mysql/bin/mysql -uroot -proot -e "create schema search charset utf8mb4"
                ;;