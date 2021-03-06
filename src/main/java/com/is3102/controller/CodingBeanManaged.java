/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;

/**
 *
 * @author Ashish
 */
import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.ICD10_Code;
import com.is3102.service.CodingBeanRemote;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
public class CodingBeanManaged {
    
    @EJB
    private CodingBeanRemote cbr;
    //list of codes returned for a disease name searched.
    private List<ICD10_Code> icdCodes;
    //caseId to search a particular case.
    private Long caseId;
    //ICD10 code entitiy for creating a new case. 
    private ICD10_Code icd10Code; 
    //diagnosis description entered by doctor when adding a new diagnosis for a case. 
    private String diagnosisDescription;
   private Long diagnosisId;
    //disease description entered by doctor used to search ICD10 code
    // also used to set disease description when adding new disease
    private String diseaseDescription;
    //collectin of all Diagnosis objects for a particular case. 
    private Collection<Diagnosis> allDiagnosis;
    //Disease code to add a new disease to the ICD10 Codes master list
    private String diseaseId; 
    //Chapter attribute to add a new disease to the ICD10 Codes master list
    private String ICDChapter; 
    //Block attribute to add a new disease to the ICD10 Codes master list
    private String ICDblock; 
    //Disease name attribute to add a new disease to the ICD10 Codes master list
    private String diseaseName; 
    //Set of all ICD10 Codes stored in the system. 
   // private Set<ICD10_Code> allicdCodes;
    private List<ICD10_Code> allicdCodes;



   /* public void DoGetMatchingCodes(){
        List<ICD10_Code> result = cbr.getMatchingCodes(diseaseDescription);
        this.seticdCodes(result);
    }  */
    
    public void DoUpdateDiagnosis (ActionEvent actionEvent){
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("test 1");
        try{
            cbr.updateDiagnosis(caseId, icd10Code, diagnosisDescription);
            context.addMessage(null, new FacesMessage("Diagnosis added(Updated) successfully"));
        }catch(Exception ex){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot update diagnosis!", null));
        }
    }
    
    public void DoListAllDiagnosis(ActionEvent actionEvent){
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            this.setAllDiagnosis(cbr.listAllDiagnosis(caseId));
                              
        }catch(Exception ex){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));

        }
    }
    
    public void DoAddCode (ActionEvent actionEvent){
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            cbr.addCode(diseaseId, ICDChapter, ICDblock, diseaseName, diseaseDescription);
            context.addMessage(null, new FacesMessage("Code added successfully"));
           }catch(Exception ex){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot add code!", null));

        }
    }        

    public void DoListAllICD10Codes(){
        //Set<ICD10_Code> allCodes = cbr.listAllCodes();
        List<ICD10_Code> allCodes = cbr.listAllCodes();
        this.setAllicdCodes(allCodes);
    }
    
    public void DoRemoveDiagnosis(ActionEvent actionEvent){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            cbr.removeDiagnosis(diagnosisId);
            context.addMessage(null, new FacesMessage("Diagnosis removed successfully"));
        }catch(Exception ex){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot remove Diagnosis!", null));
        }
    }

   // public Set<ICD10_Code> getAllicdCodes() {
   public List<ICD10_Code> getAllicdCodes() {
        return allicdCodes;
    }

    //public void setAllicdCodes(Set<ICD10_Code> allicdCodes) {
    public void setAllicdCodes(List<ICD10_Code> allicdCodes) {
        this.allicdCodes = allicdCodes;
    }

    public List<ICD10_Code> getIcdCodes() {
        return icdCodes;
    }

    public void setIcdCodes(List<ICD10_Code> icdCodes) {
        this.icdCodes = icdCodes;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(long caseId) {
        this.caseId = caseId;
    }

    public ICD10_Code getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(ICD10_Code icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getDiagnosisDescription() {
        return diagnosisDescription;
    }

    public void setDiagnosisDescription(String diagnosisDescription) {
        this.diagnosisDescription = diagnosisDescription;
    }

    public Collection<Diagnosis> getAllDiagnosis() {
        return allDiagnosis;
    }

    public void setAllDiagnosis(Collection<Diagnosis> allDiagnosis) {
        this.allDiagnosis = allDiagnosis;
    }

    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getICDChapter() {
        return ICDChapter;
    }

    public void setICDChapter(String ICDChapter) {
        this.ICDChapter = ICDChapter;
    }

    public String getICDblock() {
        return ICDblock;
    }

    public void setICDblock(String ICDblock) {
        this.ICDblock = ICDblock;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public List<ICD10_Code> geticdCodes() {
        return icdCodes;
    }

    public void seticdCodes(List<ICD10_Code> icdCodes) {
        this.icdCodes = icdCodes;
    }

    public String getDiseaseDescription() {
        return diseaseDescription;
    }

    public void setDiseaseDescription(String diseaseDescription) {
        this.diseaseDescription = diseaseDescription;
    }

    public CodingBeanRemote getCbr() {
        return cbr;
    }

    public void setCbr(CodingBeanRemote cbr) {
        this.cbr = cbr;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public void setDiagnosisId(Long diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public Long getDiagnosisId() {
        return diagnosisId;

    }


}
