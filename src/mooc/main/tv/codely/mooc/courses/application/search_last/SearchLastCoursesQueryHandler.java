package tv.codely.mooc.courses.application.search_last;

import tv.codely.mooc.courses.application.CourseService;
import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.query.QueryHandler;

@Service
public final class SearchLastCoursesQueryHandler implements QueryHandler<SearchLastCoursesQuery, CoursesResponse> {
    private final CourseService service;

    public SearchLastCoursesQueryHandler(CourseService service) {
        this.service = service;
    }

    @Override
    public CoursesResponse handle(SearchLastCoursesQuery query) {
        return service.searchLast(query.total());
    }
}
