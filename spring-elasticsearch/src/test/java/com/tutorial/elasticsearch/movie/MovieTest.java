package com.tutorial.elasticsearch.movie;

import com.tutorial.elasticsearch.movie.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieTest {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    MovieRepository movieRepository;

    @Before
    public void before() {
        elasticsearchTemplate.deleteIndex(Movie.class);
        elasticsearchTemplate.createIndex(Movie.class);
        elasticsearchTemplate.putMapping(Movie.class);
        elasticsearchTemplate.refresh(Movie.class);
    }

    @Test
    public void testSave() {
        movieRepository.save(new Movie());
        assertThat(movieRepository.findAll().getTotalElements(), is(1L));
    }

    @Test
    public void testPage() {
        movieRepository.save(new Movie());
        movieRepository.save(new Movie());
        Page<Movie> page = movieRepository.findAll(new PageRequest(0, 1));
        assertThat(page.getContent().size(), is(1));
    }
}
