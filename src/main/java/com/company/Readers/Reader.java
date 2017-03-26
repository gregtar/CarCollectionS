package com.company.Readers;

/**
 * Created by gregtar on 25.03.17.
 */
import java.util.Date;

public interface Reader {
    public String readName();

    public String readSurname();

    public String readPhone();

    public Date readBirthDate();
}
