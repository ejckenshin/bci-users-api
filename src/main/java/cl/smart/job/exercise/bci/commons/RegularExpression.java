package cl.smart.job.exercise.bci.commons;

public class RegularExpression {

    public static final String EMAIL = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
    public static final String NAMES ="^([A-Za-zÑñÁáÉéÍíÓóÚú]+['\\-]?[A-Za-zÑñÁáÉéÍíÓóÚú]+)(\\s+([A-Za-zÑñÁáÉéÍíÓóÚú]+['\\-]?[A-Za-zÑñÁáÉéÍíÓóÚú]+))*$";
    public static final String ONLY_DIGITS_NUMERICS = "^\\d+$";
    public static final String KEY_PASSWORD = "${regex.password}";

}
