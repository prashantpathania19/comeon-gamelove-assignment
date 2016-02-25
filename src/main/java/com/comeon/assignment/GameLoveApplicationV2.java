package com.comeon.assignment;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.comeon.assignment.persistence.GameDao;
import com.comeon.assignment.persistence.GameTrackingDao;
import com.comeon.assignment.persistence.PlayerDao;
import com.comeon.assignment.representations.Game;
import com.comeon.assignment.representations.GameTracking;
import com.comeon.assignment.representations.Player;
import com.comeon.assignment.resources.GameTrackingResource;

/**
 * This class is used for GameLove Application
 * @author Prashant Pathania
 */
public class GameLoveApplicationV2 extends Application<DatabaseConfiguration>
{
    public static void main( String[] args ) throws Exception
    {
        new GameLoveApplicationV2().run(args);
    }

    private final HibernateBundle<DatabaseConfiguration> hibernate = new HibernateBundle<DatabaseConfiguration>(Player.class, Game.class, GameTracking.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DatabaseConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    /* (non-Javadoc)
     * @see io.dropwizard.Application#initialize(io.dropwizard.setup.Bootstrap)
     */
    @Override
    public void initialize(Bootstrap<DatabaseConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    /* (non-Javadoc)
     * @see io.dropwizard.Application#run(io.dropwizard.Configuration, io.dropwizard.setup.Environment)
     */
    @Override
    public void run(DatabaseConfiguration databaseConfiguration, Environment environment) throws Exception {
        PlayerDao playerDao = new PlayerDao(hibernate.getSessionFactory());
        GameDao gameDao = new GameDao(hibernate.getSessionFactory());
        GameTrackingDao gameTrackingDao = new GameTrackingDao(hibernate.getSessionFactory());
        environment.jersey().register(new GameTrackingResource(playerDao, gameDao, gameTrackingDao));
    }
}
