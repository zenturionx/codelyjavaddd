package tv.codely.mooc.courses.application.find;

import tv.codely.mooc.courses.application.CourseResponse;
import tv.codely.mooc.courses.application.CourseService;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseNotExist;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.query.QueryHandler;

@Service
public final class FindCourseQueryHandler implements QueryHandler<FindCourseQuery, CourseResponse> {
    private final CourseService service;

    public FindCourseQueryHandler(CourseService service) {
        this.service = service;
    }

    @Override
    public CourseResponse handle(FindCourseQuery query) throws CourseNotExist {
        return service.find(new CourseId(query.id()));
    }
}
