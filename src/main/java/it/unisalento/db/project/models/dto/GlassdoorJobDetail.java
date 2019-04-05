
package it.unisalento.db.project.models.dto;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class GlassdoorJobDetail {

    @SerializedName("header")
    private Header mHeader;
    @SerializedName("job")
    private Job mJob;
    @SerializedName("salary")
    private Salary mSalary;

    public Header getHeader() {
        return mHeader;
    }

    public void setHeader(Header header) {
        mHeader = header;
    }

    public Job getJob() {
        return mJob;
    }

    public void setJob(Job job) {
        mJob = job;
    }

    public Salary getSalary() {
        return mSalary;
    }

    public void setSalary(Salary salary) {
        mSalary = salary;
    }

}
