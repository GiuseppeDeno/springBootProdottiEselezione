package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

    ArrayList<prodottoUrl> listaProdotti = new ArrayList<>();

  
   
   @PostMapping("/submit")  // gestiamo la chiamata di tipo POST del form
    public String getDati(@RequestParam("nome") String nome, @RequestParam("marca") String marca,
                          @RequestParam("prezzo") int prezzo ,@RequestParam("url")String url, Model m1) {

	 
        // Creazione oggetto prodotto
        prodottoUrl p1 = new prodottoUrl(nome, marca, prezzo,url);
        listaProdotti.add(p1);  // Aggiungo il prodotto alla lista
        
        // Passo il prodotto al model per stamparlo
        m1.addAttribute("prodotto", p1);
        
        // Ritorniamo una pagina per visualizzare il prodotto (potresti aver bisogno di un file HTML "stampaProdotto.html")
        return "stampaProdotto";  
        
        ///NOTA IMP se volessi che questo metodo mi prendesse i dati senza dover stampare nella pagina stampaProdotto posso fare cosi
        //1. public void getDati.. quindi metodo void e pooi
        //2.  return "redirect:/"; // Bisogna  avere un metodo mappato per "/" che mi reindirizza al index dove ce appunto il form
    }
    
    
    
    ///prendiamo un numero di prodotti selezionati tramite il form 
    ///numeri è il numerdo di prodotti che abbiamo schelto con quel nome. lo mettiamo in un array list di integer cosi verra stampato 
    //faccio richiesta dei parametri di nome e numero (quantita di prodotto) gli altri sono superflui 
    @PostMapping("/process")
    public String getProdottiSelezionati(@RequestParam("nome") ArrayList<String> nomi, 
                                       @RequestParam("num") ArrayList<Integer> numeri,
                                       Model m1) {
        ArrayList<prodottoUrl> prodottiSelezionati = new ArrayList<>();

        for (int i = 0; i < nomi.size(); i++) {
            if (numeri.get(i) != 0) {
                // Trova il prodotto corrispondente aggiungendolo ai prodotti selezionati
                for (prodottoUrl prodotto : listaProdotti) {
                    if (prodotto.getNome().equals(nomi.get(i))) {
                        prodottiSelezionati.add(prodotto);
                    }
                }
            }
        }
        
        m1.addAttribute("prodottiSelezionati", prodottiSelezionati);
        // volendo stampare anche il catalogo di prodotti
        m1.addAttribute("lista", listaProdotti);
        return "stampaCard"; 
    }

    // Stampiamo la lista dei prodotti
    //mappiamo /listaProdottiform che è collegato al"bottone" che premuto mi deve restiture la lista di prodotti. 
    //lo fa chiamando la lista come attributo del model e ritornando una view , una pagina html stampaLista 
    
   
    @GetMapping("/listaProdottiForm") // mapping alla rotta listaProdottiForm
    public String getLista(Model m1) {
        m1.addAttribute("lista", listaProdotti);
      //rimanda alla pagina di stampa tramite semplici ul  return "stampaLista";
        
        //rimando alla pagina che stampa le card di materialize
        return "stampaCard";
        
    }
    
    
    @GetMapping("/")   // metodo per reindirizzare al index, ovvero dove si trova il form . mappando "/"
    public String getIndex(Model m1) {
    	
    	  //pulisco la lista prodotti cosi non la aggiunge ogni volta che refresh
 	   listaProdotti.removeAll(listaProdotti);
 	   
 	 
 	   
 	   //istanzio due prodotti 
 	   //le immagini vanno in static 
 	    listaProdotti.add(new prodottoUrl("pane","pane", 1,"pane.jpeg"));
 	    listaProdotti.add(new prodottoUrl("cell","samsung",200,"samsung.jpeg"));
 	   /*
		 * La passiamo al model con la dicitura nome
		 * assegniamo alla variabile nome il suo valore
		 */
 	// Aggiungi la lista dei prodotti al modello
 	    m1.addAttribute("listaProdotti", listaProdotti);
	//perche?
        return "index";
    }

}
