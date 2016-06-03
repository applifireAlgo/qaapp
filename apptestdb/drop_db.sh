




echo $PATH
DB_PATH=/tmp/applifire/db/NNZSXNRLL8FASJXYCNFQ/860A78FA-056D-4D56-9427-2BE36DD0A56E
MYSQL=/usr/bin
USER=algo
PASSWORD=algo
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'