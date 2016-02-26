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
import com.comeon.assignment.representations.Player;

/**
 * This class contains JUnit test cases for PlayerDao
 * @author Prashant Pathania
 */
@RunWith(MockitoJUnitRunner.class)
public class TestPlayerDao {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Query query;
    @InjectMocks
    private PlayerDao playerDao;
    private String playerName = "test";

    /**
     * This method is called before executing JUnit test case
     * @return void
     */
    @Before
    public void setUp() {
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
        Mockito.when(session.getNamedQuery((String)Mockito.any())).thenReturn(query);
        playerDao = new PlayerDao(sessionFactory);
    }

    /**
     * Method Under test: savePlayer(String)
     * Scenario: when valid Player name is provided
     * Expectation: saveOrUpdate method will be called once to save to data
     */
    @Test
    public void testSavePlayer() throws Exception {
        playerDao.savePlayer(playerName);
        Mockito.verify(session, Mockito.times(1)).saveOrUpdate((Player)Mockito.any());
    }

    /**
     * Method Under test: findPlayer(String)
     * Scenario: when player is found
     * Expectation: it will return player object
     */
    @Test
    public void testFindPlayerWhenPlayerIsFound() throws Exception {
        List<Player> playerList = new ArrayList<Player>();
        playerList.add(new Player());
        Mockito.when(query.list()).thenReturn(playerList);
        Assert.assertNotNull(playerDao.findPlayer(playerName));
        Assert.assertTrue(!playerList.isEmpty());
    }

    /**
     * Method Under test: findPlayer(String)
     * Scenario: when player is not found
     * Expectation: it will return null
     */
    @Test
    public void testFindPlayerWhenPlayerIsNotFound() throws Exception {
        Mockito.when(query.list()).thenReturn(null);
        Assert.assertNull(playerDao.findPlayer(playerName));
    }

    /**
     * This method is called after every JUnit test case to release memory
     * @return void
     */
    @After
    public void tearDown() {
        sessionFactory = null;
        session = null;
        playerDao = null;
    }
}
