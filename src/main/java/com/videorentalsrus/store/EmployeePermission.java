package com.videorentalsrus.store;

import java.io.Serializable;

public class EmployeePermission implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer employeePermissionId;
	private Integer employeePermissionRoleId;
	private Boolean canAddVideo;
	private Boolean canDeleteVideo;
	private Boolean canRentVideo;
	private Boolean canDeleteRental;
	private Boolean canDischareBalance;
	private Boolean canAddEmployee;
	private Boolean canRemoveEmployee;
	private Boolean canUpdateVideoStatus;
	private Boolean canUpdateCustomer;
	

}
