package com.mubasshir.adminhomenew;

public class ProjectList {
    private String mproject_name;
    private String mamount_in;
    private String mamount_out;

    public ProjectList(String project_name, String amount_in, String amount_out) {
        mproject_name = project_name;
        mamount_in = amount_in;
        mamount_out = amount_out;
    }

    public String getproject_name(){
        return mproject_name;
    }

    public String getamount_in(){
        return mamount_in;
    }

    public String getamount_out(){
        return mamount_out;
    }
}
