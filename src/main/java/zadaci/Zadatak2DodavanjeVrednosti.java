package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Zadatak2DodavanjeVrednosti {

    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer> robaDao;

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            avionDao = DaoManager.createDao(connectionSource, Avion.class);
            robaDao = DaoManager.createDao(connectionSource, Roba.class);


            TableUtils.clearTable(connectionSource, Avion.class);
            TableUtils.clearTable(connectionSource, Roba.class);


            Avion a1=new Avion("Avion1",34);
            avionDao.create(a1);

            Avion a2=new Avion("Avion2",21);
            avionDao.create(a2);

            Roba r1 = new Roba("Patike", "Duboke patike",1, a1);
            robaDao.create(r1);

            Roba r2 = new Roba("Kosulja", "Na duge rukave",0.4, a1);
            robaDao.create(r2);

            Roba r3 = new Roba("Voda", "Voda za pice",1.4, a1);
            robaDao.create(r3);

            Roba r4 = new Roba("Ploce", "Drvene ploce",3.4, a2);
            robaDao.create(r4);

            Roba r5 = new Roba("Stolica", "Plasticna stolica",2.4, a2);
            robaDao.create(r5);

            List<Avion> avion = avionDao.queryForAll();
            for (Avion a : avion)
                System.out.println(a);

            List<Roba> roba = robaDao.queryForAll();
            for (Roba r : roba)
                System.out.println(r);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
