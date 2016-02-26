/**
 * 
 */
package com.comeon.assignment.persistence;

import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.comeon.assignment.representations.Game;
import com.comeon.assignment.representations.GameTracking;
import com.comeon.assignment.representations.Player;

/**
 * This class contains JUnit test cases for GameTrackingDao
 * @author Prashant Pathania
 */
@RunWith(MockitoJUnitRunner.class)
public class TestGameTrackingDao {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Query query;
    @InjectMocks
    private GameTrackingDao gameTrackingDao;

    /**
     * This method is called before executing JUnit test case
     * @return void
     */
    @Before
    public void setUp() {
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
        Mockito.when(session.getNamedQuery((String)Mockito.any())).thenReturn(query);
        gameTrackingDao = new GameTrackingDao(sessionFactory);
    }

    /**
     * Method Under test: saveGameTracking(Game game, Player player)
     * Scenario: when valid game and player are saved
     * Expectation: saveOrUpdate method will be called once to save to data
     */
    @Test
    public void testSaveGameTracking() throws Exception {
        gameTrackingDao.saveGameTracking(new Game(), new Player());
        Mockito.verify(session, Mockito.times(1)).saveOrUpdate((Game)Mockito.any());
    }

    /**
     * Method Under test: listTracking()
     * Scenario: when tracking is available
     * Expectation: it will return a list of tracking
     */
    @Test
    public void testListTrackingWhenTrackingisAvailable() throws Exception {
        Mockito.when(query.list()).thenReturn(new ArrayList<GameTracking>());
        Assert.assertNotNull(gameTrackingDao.listTracking());
    }

    /**
     * Method Under test: listTracking()
     * Scenario: when tracking is not available
     * Expectation: it will return null
     */
    @Test
    public void testListTrackingWhenTrackingisNotAvailable() throws Exception {
        Mockito.when(query.list()).thenReturn(null);
        Assert.assertNull(gameTrackingDao.listTracking());
    }

    /**
     * Method Under test: getTopGames()
     * Scenario: when top like games are available
     * Expectation: it will return a list of top liked games
     */
    @Test
    public void testGetTopGamesWhenTopGamesAreAvailable() throws Exception {
        Mockito.when(query.list()).thenReturn(new ArrayList<Object>());
        Assert.assertNotNull(gameTrackingDao.getTopGames());
    }

    /**
     * Method Under test: getTopGames()
     * Scenario: when top like games are not available
     * Expectation: it will return null
     */
    @Test
    public void testGetTopGamesWhenTopGamesAreNotAvailable() throws Exception {
        Mockito.when(query.list()).thenReturn(null);
        Assert.assertNull(gameTrackingDao.getTopGames());
    }

    /**
     * This method is called after every JUnit test case to release memory
     * @return void
     */
    @After
    public void tearDown() {
        sessionFactory = null;
        session = null;
        gameTrackingDao = null;
    }
}
