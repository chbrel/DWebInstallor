package models;
import java.util.HashMap;
import java.util.TreeMap;


public enum GameCategory {
    PLATEFORME("Plate-Forme"), REFLEXION("Réflexion"),
    MULTIJEUX("Multi-jeux"), ROLE("Jeu de rôle"),
    STRATEGIE("Stratégie"), ATELIER("Atelier DeViNT"),
    ARCADE("Arcade"), REFLEXE("Réflexe"),
    CONDUITE("Conduite"), DESSIN("Dessin"),
    CONNAISSANCES("Connaissances"),
    MEMOIRE("Mémoire"), MUSIQUE("Musique"),
    RAPIDITE("Rapidité"), MOUVEMENT("Mouvement"),
    LETTRES("Lettres"), CALCUL("Calcul"),
    LOGIQUE("Logique"), TEXTUEL("Jeu textuel"),
    PUZZLE("Puzzle");
    
    // The categorie string.
    private String categorie;
    
    public static final TreeMap<String, GameCategory> validCategory = createValidCategorie();
    
    /**
     * Initialise with the corresponding categorie.
     * @param categorie The categorie string.
     */
    GameCategory(String categorie)
    {
        this.categorie = categorie;
    }
    
    private static TreeMap<String, GameCategory> createValidCategorie() {
        TreeMap<String, GameCategory> tm = new TreeMap<String, GameCategory>();
        for(GameCategory categorie : GameCategory.values()) {
            tm.put(categorie.toString(), categorie);
        }
        
        //HashMap<String, GameCategory> hm = new HashMap<String, GameCategory>(tm);
        
        return tm;
    }

    /**
     * @return The categorie as a string.
     */
    public String toString()
    {
        return categorie;
    }   
}
