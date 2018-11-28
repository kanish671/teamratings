package anishk.developer.teamratings.utils;

import java.util.Date;

public class DateUtils {

    public boolean isNull(Date param) {
        return (param == null || param.toString().isEmpty());
    }
}
