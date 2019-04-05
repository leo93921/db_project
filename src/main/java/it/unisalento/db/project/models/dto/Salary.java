
package it.unisalento.db.project.models.dto;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Salary {

    @SerializedName("salaries")
    private Object mSalaries;

    public Object getSalaries() {
        return mSalaries;
    }

    public void setSalaries(Object salaries) {
        mSalaries = salaries;
    }

}
