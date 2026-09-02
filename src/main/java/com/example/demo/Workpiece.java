package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "workpieces")
public class Workpiece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String material;
    private String fixture;
    private String orientation;

    @Column(name = "work_offset")
    private String workOffset;

    private String status;

    @Column(name = "fixture_status")
    private String fixtureStatus;

    @Column(name = "orientation_status")
    private String orientationStatus;

    @Column(name = "clamping_status")
    private String clampingStatus;

    @Column(name = "material_status")
    private String materialStatus;

    @Column(name = "work_offset_status")
    private String workOffsetStatus;

    public Workpiece() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getFixture() {
        return fixture;
    }

    public void setFixture(String fixture) {
        this.fixture = fixture;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getWorkOffset() {
        return workOffset;
    }

    public void setWorkOffset(String workOffset) {
        this.workOffset = workOffset;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFixtureStatus() {
        return fixtureStatus;
    }

    public void setFixtureStatus(String fixtureStatus) {
        this.fixtureStatus = fixtureStatus;
    }

    public String getOrientationStatus() {
        return orientationStatus;
    }

    public void setOrientationStatus(String orientationStatus) {
        this.orientationStatus = orientationStatus;
    }

    public String getClampingStatus() {
        return clampingStatus;
    }

    public void setClampingStatus(String clampingStatus) {
        this.clampingStatus = clampingStatus;
    }

    public String getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(String materialStatus) {
        this.materialStatus = materialStatus;
    }

    public String getWorkOffsetStatus() {
        return workOffsetStatus;
    }

    public void setWorkOffsetStatus(String workOffsetStatus) {
        this.workOffsetStatus = workOffsetStatus;
    }
}