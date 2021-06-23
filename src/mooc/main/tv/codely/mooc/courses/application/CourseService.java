package tv.codely.mooc.courses.application;

import tv.codely.mooc.courses.domain.*;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.domain.criteria.Criteria;
import tv.codely.shared.domain.criteria.Filters;
import tv.codely.shared.domain.criteria.Order;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository repository;
    private final EventBus         eventBus;

    public CourseService(CourseRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus   = eventBus;
    }

    public void create(CourseId id, CourseName name, CourseDuration duration) {
        Course course = Course.create(id, name, duration);

        repository.save(course);
        eventBus.publish(course.pullDomainEvents());
    }

    public CourseResponse find(CourseId id) throws CourseNotExist {
        Optional<Course> course = repository.search(id);

        if (course.isPresent()) {
            return CourseResponse.fromAggregate(course.get());
        } else {
            throw new CourseNotExist(id);
        }
    }

    public CoursesResponse searchLast(int courses) {
        Criteria criteria = new Criteria(
            Filters.none(),
            Order.none(),
            Optional.of(courses),
            Optional.empty()
        );

        return new CoursesResponse(
            repository.matching(criteria).stream().map(CourseResponse::fromAggregate).collect(Collectors.toList())
        );
    }
}
