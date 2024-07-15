package org.pattern.commandservice.context;


import org.apache.commons.lang3.StringUtils;

public class RequesterThreadLocalContext {

    public static final ThreadLocal<String> requestContext = new ThreadLocal<>();

    public static final ThreadLocal<DataSource> dataSourceContext = new ThreadLocal<>();

    private RequesterThreadLocalContext() {
    }

    public static void setRequesterName(String requesterName) {
        if (StringUtils.isNotBlank(requesterName)) {
            requestContext.set(requesterName);
        } else {
            requestContext.set(StringUtils.EMPTY);
        }
    }

    public static String getRequesterName() {
        return requestContext.get() != null ? requestContext.get() : StringUtils.EMPTY;
    }

    public static void setDataSource(DataSource dataSource) {
        dataSourceContext.set(dataSource);
    }

    public static DataSource getDataSource() {
        return dataSourceContext.get();
    }

    public static void clear() {
        requestContext.remove();
    }

    public static void clearDataSource() {
        dataSourceContext.remove();
    }
}
