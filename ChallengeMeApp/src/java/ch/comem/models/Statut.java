/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.models;


/**
 *
 * @author bastieneichenberger
 */
public enum Statut {
    
    ENCOURS, ABANDONNE, REUSSIE, A_VALIDE;
    
    @Override
    public String toString(){
        return this.name();
    }
}
