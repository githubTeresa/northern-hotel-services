package com.northern.hotel.services.entity;

public class StaffMemberModel {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;

    public StaffMemberModel() {
    }

    public StaffMemberModel(String employeeId, String firstName, String lastName, String position) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public StaffMember translateToStaffMember() {
        StaffMember staffMember = new StaffMember();
        staffMember.setEmployeeId(employeeId);
        staffMember.setFirstName(firstName);
        staffMember.setLastName(lastName);
        staffMember.setPosition(position);

        return staffMember;
    }
}