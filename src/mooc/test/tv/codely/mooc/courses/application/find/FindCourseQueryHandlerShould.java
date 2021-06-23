package tv.codely.mooc.courses.application.find;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.CoursesModuleUnitTestCase;
import tv.codely.mooc.courses.application.CourseResponse;
import tv.codely.mooc.courses.application.CourseResponseMother;
import tv.codely.mooc.courses.application.CourseService;
import tv.codely.mooc.courses.domain.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class FindCourseQueryHandlerShould extends CoursesModuleUnitTestCase {
    private FindCourseQueryHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        handler = new FindCourseQueryHandler(new CourseService(repository, eventBus));
    }

    @Test
    void throw_an_exception_when_a_course_doesnt_exist() {
        FindCourseQuery query = FindCourseQueryMother.random();

        CourseId id = CourseIdMother.create(query.id());

        shouldSearch(id);

        assertThrows(CourseNotExist.class, () -> handler.handle(query));
    }

    @Test
    void search_an_existing_course() {
        FindCourseQuery query = FindCourseQueryMother.random();

        Course         course   = CourseMother.withId(query.id());
        CourseResponse response = CourseResponseMother.create(course.id(), course.name(), course.duration());

        shouldSearch(course);

        assertEquals(response, handler.handle(query));
    }
}
