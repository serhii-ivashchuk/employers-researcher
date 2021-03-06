package pro.ivashchuk.employerresearcher.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pro.ivashchuk.employerresearcher.domain.Vacancy;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class JpaVacancyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    JpaVacancyRepository jpaVacancyRepository;

    @Test
    public void shouldFindAllVacancies() {
        Vacancy testVacancy1 = getVacancy("Junior Java Engineer");
        Vacancy testVacancy2 = getVacancy("Java Engineer");
        Vacancy testVacancy3 = getVacancy("Senior Java Engineer");
        List<Vacancy> initialEmployers = Arrays.asList(testVacancy1, testVacancy2, testVacancy3);
        entityManager.persist(testVacancy1);
        entityManager.persist(testVacancy2);
        entityManager.persist(testVacancy3);
        entityManager.flush();
        int vacanciesQuantity = jpaVacancyRepository.findAll().size();
        List<Vacancy> foundVacancies = jpaVacancyRepository.findAll();

        assertEquals(foundVacancies.size(), vacanciesQuantity, "When repository stores 3 vacancies, then it " +
                "should return list of 3 Vacancies");
    }

    @Test
    public void shouldFindVacancyById() {
        Vacancy testVacancy = getVacancy("Junior Java Engineer");
        entityManager.persist(testVacancy);
        Long testVacancyId = (Long) entityManager.getId(testVacancy);
        entityManager.flush();
        Vacancy foundVacancy = jpaVacancyRepository.findById(testVacancy.getId()).get();

        assertEquals(foundVacancy.getPosition(), testVacancy.getPosition(), "Vacancy before persistence should be " +
                "equal to vacancy from repository after persistence.");
    }

    @Test
    public void shouldSave() {
        Vacancy testVacancy = getVacancy("Junior Java Engineer");
        assertThat(testVacancy, is(notNullValue()));
        entityManager.persist(testVacancy);
        entityManager.flush();
        Vacancy savedVacancy = jpaVacancyRepository.save(testVacancy);

        assertThat(savedVacancy, is(notNullValue()));
        assertEquals(testVacancy, savedVacancy, "Test vacancy should be saved");
    }

    @Test
    public void shouldUpdateVacancy() {
        Vacancy vacancyBeforeUpdate = jpaVacancyRepository.save(getVacancy("Junior Java Engineer"));
        Vacancy vacancyAfterUpdate = jpaVacancyRepository.save(createUpdatedVacancy(vacancyBeforeUpdate, "Java" +
                " Engineer"));

        assertEquals("Java Engineer", vacancyAfterUpdate.getPosition(), "After update position should be equal");
        assertEquals(vacancyBeforeUpdate.getId(), vacancyAfterUpdate.getId(), "Id's should be equal");
    }

    @Test
    public void shouldDelete() {
        Vacancy testVacancy = getVacancy("Junior Java Engineer");
        entityManager.persist(testVacancy);
        entityManager.flush();
        jpaVacancyRepository.delete(testVacancy);

        assertEquals(jpaVacancyRepository.findAll().size(), 0, "The repository should have 0 Vacancy entity");
    }

    @Test
    public void shouldDeleteById() {
        Vacancy testVacancy = getVacancy("Junior Java Engineer");
        entityManager.persist(testVacancy);
        entityManager.flush();
        Long testVacancyId = (Long) entityManager.getId(testVacancy);
        jpaVacancyRepository.deleteById(testVacancyId);

        assertEquals(jpaVacancyRepository.findAll().size(), 0, "The repository after deletion should have 0 Vacancy" +
                " entity");
    }

    private Vacancy getVacancy(String position) {
        return new Vacancy(
                position,
                "theBestEmployer.com/vacancies/1",
                "Notes: this position is relevant to my search",
                "theBestEmployer is hiring now! For description please visit our website theBestEmployer" +
                        ".com/vacancies.",
                "Comment: it's very suitable");
    }

    private Vacancy createUpdatedVacancy(Vacancy vacancy, String newPosition) {
        vacancy.setPosition(newPosition);
        return vacancy;
    }
}