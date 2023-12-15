import tkinter as tk

def copier_texte():
    # Récupérer le contenu du premier widget Text
    contenu_texte = texte1.get("1.0", "end-1c")

    # Effacer le contenu du deuxième widget Text
    texte2.delete("1.0", "end")

    # Copier le contenu du premier widget vers le deuxième
    texte2.insert("1.0", contenu_texte)

# Création de la fenêtre principale
fenetre = tk.Tk()
fenetre.title("Copie de texte avec Tkinter")

# Création du premier widget Text
texte1 = tk.Text(fenetre, height=5, width=40)
texte1.pack()

# Création du deuxième widget Text
texte2 = tk.Text(fenetre, height=5, width=40)
texte2.pack()

# Création du bouton pour copier le texte
bouton_copier = tk.Button(fenetre, text="Copier Texte", command=copier_texte)
bouton_copier.pack()

# Lancement de la boucle principale de l'interface graphique
fenetre.mainloop()
