package tv.codely.mooc.courses.application.find;

import tv.codely.shared.domain.UuidMother;

final class FindCourseQueryMother {
    public static FindCourseQuery create(String id) {
        return new FindCourseQuery(id);
    }

    public static FindCourseQuery random() {
        return create(UuidMother.random());
    }
}
