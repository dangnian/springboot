package com.dangnian.springboot.entity.test.po;

/**
 * @Author chun.yin
 **/

public class TestPO {

    private String testCode;

    private String testName;

    private String testRemark;

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestRemark() {
        return testRemark;
    }

    public void setTestRemark(String testRemark) {
        this.testRemark = testRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestPO testPO = (TestPO) o;

        if (testCode != null ? !testCode.equals(testPO.testCode) : testPO.testCode != null) return false;
        if (testName != null ? !testName.equals(testPO.testName) : testPO.testName != null) return false;
        return testRemark != null ? testRemark.equals(testPO.testRemark) : testPO.testRemark == null;
    }

    @Override
    public int hashCode() {
        int result = testCode != null ? testCode.hashCode() : 0;
        result = 31 * result + (testName != null ? testName.hashCode() : 0);
        result = 31 * result + (testRemark != null ? testRemark.hashCode() : 0);
        return result;
    }
}
