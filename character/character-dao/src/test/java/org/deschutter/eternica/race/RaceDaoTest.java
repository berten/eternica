package org.deschutter.eternica.race;

import org.deschutter.eternica.init.DBConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DBConfig.class})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class RaceDaoTest {
    @Autowired
    private RaceDao raceDao;

    @Test
    public void save_findOne() {
        RaceEntity entity = raceDao.save(new RaceEntity("name"));
        RaceEntity retrieved = raceDao.findOne(entity.getId());
        assertThat(retrieved, allOf(hasProperty("name", is(entity.getName())), hasProperty("id", is(entity.getId()))));
    }
}
