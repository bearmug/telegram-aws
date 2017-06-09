package org.bearmug.aws.model;

public enum CommandType {

    /* basic commands */
    START("Start tour"),
    HELP("Get some help"),
    UNKNOWN("unknown"),

    /* behavior type commands */
    GUIDE("guide"),
    VISITOR("visitor"),

    /* guide-related commands */
    INVITE("invite"), // not implemented
    EXTRAS("guided tour extras"),
    SHARE_CONTEXT("g_context"),
    SHARE_LOCATION("update tourists with guide location"),
    G_CONTEXT_TOUR("g_tour"),
    INFO_INFO("share related information"),
    INFO_GUIDE("g_myself"),
    INFO_ORG("g_org"),
    INFO_MORE("g_more"),
    POST("dummy update"),

    /* visitor-related commands */
    INFO("v_info"),
    INFO_TOUR("v_tour"),
    METRO("metro location and route request"),
    LOST("locate guide and navigate there"),
    INFO_EXTRAS("v_extras");

    final String description;

    CommandType(String description) {
        this.description = description;
    }
}