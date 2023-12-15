import tkinter as tk

# Fonction appelée lorsque le bouton est cliqué
def on_button_click():
    label.config(text="Bonjour, " + entry.get())

# Création de la fenêtre principale
fenetre = tk.Tk()
fenetre.title("Ma première interface graphique avec Tkinter")

# Création d'un label
label = tk.Label(fenetre, text="Entrez votre nom :")
label.pack()

# Création d'une zone de saisie (Entry)
entry = tk.Entry(fenetre)
entry.pack()

# Création d'un bouton
button = tk.Button(fenetre, text="Cliquez-moi !", command=on_button_click)
button.pack()

# Lancement de la boucle principale de l'interface graphique
fenetre.mainloop()