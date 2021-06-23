package tv.codely.mooc.courses;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.infrastructure.UnitTestCase;

import java.util.Optional;

import static org.mockito.Mockito.*;

public abstract class CoursesModuleUnitTestCase extends UnitTestCase {
    protected CourseRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(CourseRepository.class);
    }

    public void shouldHaveSaved(Course course) {
        verify(repository, atLeastOnce()).save(course);
    }

    public void shouldSearch(Course course) {
        Mockito.when(repository.search(course.id())).thenReturn(Optional.of(course));
    }

    public void shouldSearch(CourseId id) {
        Mockito.when(repository.search(id)).thenReturn(Optional.empty());
    }
}
