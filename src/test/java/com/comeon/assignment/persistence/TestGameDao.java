/**
 * 
 */
package com.comeon.assignment.persistence;

import java.util.ArrayList;
import java.util.List;
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

/**
 * This class contains JUnit test cases for GameDao
 * @author Prashant Pathania
 */
@RunWith(MockitoJUnitRunner.class)
public class TestGameDao {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Query query;
    @InjectMocks
    private GameDao gameDao;
    private String gameName = "test";

    /**
     * This method is called before executing JUnit test case
     * @return void
     */
    @Before
    public void setUp() {
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
        Mockito.when(session.getNamedQuery((String)Mockito.any())).thenReturn(query);
        gameDao = new GameDao(sessionFactory);
    }

    /**
     * Method Under test: saveGame(String)
     * Scenario: when valid game name is provided
     * Expectation: saveOrUpdate method will be called once to save to data
     */
    @Test
    public void testSaveGame() throws Exception {
        gameDao.saveGame(gameName);
        Mockito.verify(session, Mockito.times(1)).saveOrUpdate((Game)Mockito.any());
    }

    /**
     * Method Under test: findGame(String)
     * Scenario: when game is found
     * Expectation: it will return game object
     */
    @Test
    public void testFindGameWhenGameIsFound() throws Exception {
        List<Game> gameList = new ArrayList<Game>();
        gameList.add(new Game());
        Mockito.when(query.list()).thenReturn(gameList);
        Assert.assertNotNull(gameDao.findGame(gameName));
        Assert.assertTrue(!gameList.isEmpty());
    }

    /**
     * Method Under test: findGame(String)
     * Scenario: when game is not found
     * Expectation: it will return null
     */
    @Test
    public void testFindGameWhenGameIsNotFound() throws Exception {
        Mockito.when(query.list()).thenReturn(null);
        Assert.assertNull(gameDao.findGame(gameName));
    }

    /**
     * This method is called after every JUnit test case to release memory
     * @return void
     */
    @After
    public void tearDown() {
        sessionFactory = null;
        session = null;
        gameDao = null;
    }
}
