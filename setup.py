import pymysql
import sys


def createDB(ac, psw):
    db = pymysql.connect('127.0.0.1', ac, psw, 'demo')

    with db:
        cur = db.cursor()
        cur.execute("create table if not exists User(id int PRIMARY KEY auto_increment"
                    ",account varchar(255) not NULL "
                    ",password varchar(255) not NULL "
                    ",name VARCHAR(255) not NULL "
                    ",age int not NULL )")


if __name__ == "__main__":
    if sys.argv.__len__() < 3:
        print("please input account and password !")
        exit()
    account = sys.argv[1]
    password = sys.argv[2]

    createDB(account, password)