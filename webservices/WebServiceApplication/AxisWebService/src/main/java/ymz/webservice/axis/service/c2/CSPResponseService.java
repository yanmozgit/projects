/**
 * CSPResponseService.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package ymz.webservice.axis.service.c2;

import javax.jws.WebParam;

/**
 * CSP响应接口
 */
public class CSPResponseService {

    /**
     * 回调通知
     * @param CSPID
     * @param LSPID
     * @param correlateID
     * @param cmdResult
     * @param resultFileURL
     * @return
     */
    public CSPResult resultNotify(String CSPID, String LSPID, String correlateID, int cmdResult, String resultFileURL) {
        CSPResult cspResult = new CSPResult();
        cspResult.setResult(0);
        cspResult.setErrorDescription("成功");
        System.out.println("CSPID：" + CSPID + "|LSPID：" + LSPID + "|correlateID：" + correlateID + "|cmdResult：" +  cmdResult + "|resultFileURL：" + resultFileURL);
        System.out.println("==ymz 回调成功！");
        return cspResult;
    }

}
