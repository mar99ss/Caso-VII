/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Carlos Vega
 */
public class Ranking {
  private ArrayList<Conjunto> ranking;

  public Ranking() {
    ranking = new ArrayList<>();
  }
  
  
  public ArrayList<Conjunto> getSortedRankingBysucced() {         
    Collections.sort(ranking, Conjunto.succedComparator);         
    return ranking;     
  }     

  public ArrayList<Conjunto> getRanking() {
    return ranking;
  }
  
  
  
}
