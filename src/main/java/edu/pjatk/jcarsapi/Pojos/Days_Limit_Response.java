package edu.pjatk.jcarsapi.Pojos;

public class Days_Limit_Response {
    private String name;

    private Integer limit;


    public Days_Limit_Response(String name, Integer limit) {
        this.name = name;
        this.limit = limit;
    }

    public Days_Limit_Response() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
