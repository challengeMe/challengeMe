/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import javax.ejb.Local;

/**
 *
 * @author Leo
 */
@Local
public interface ApplicationsManagerLocal {

    Long createApplication(String name, String description, String apiKey, String apiSecret);
    Long changeApplication(String name, String description, String apiKey, String apiSecret, String appId);
    Long removeApplication(String appId);
    
}
