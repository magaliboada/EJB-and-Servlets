/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import extra.ElementoRanking;
import java.util.Comparator;

/**
 *
 * @author Magali
 */
public class CustomComparator implements Comparator<ElementoRanking> {
    @Override
    public int compare(ElementoRanking o1, ElementoRanking o2) {
        return o1.getMensajesEnviados() - (o2.getMensajesEnviados());
    }
}
