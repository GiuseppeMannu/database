package com.database.utilities;

import lombok.Getter;
import lombok.Setter;

public class EmployeeToJobAssociation {
    @Getter
    @Setter
    private long employeeId;

    @Getter
    @Setter
    private long jobId;
}
