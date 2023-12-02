import os

# Nom du dossier à créer
dossier = 'D:/iwa project/python/handling_files/exercice_fi'
    # Créer le dossier s'il n'existe pas déjà
os.mkdir(dossier)
print(f"Dossier '{dossier}' créé avec succès.")

    # Noms des fichiers à ajouter
fichiers = ['fichier1.txt', 'fichier2.txt', 'fichier3.txt']

    # Ajouter les fichiers dans le dossier
for fichier in fichiers:
        chemin_fichier = os.path.join(dossier, fichier)
        with open(chemin_fichier, 'w') as f:
            f.write('Contenu du fichier')

        print(f"Fichier '{fichier}' ajouté avec succès dans le dossier.")    

    # Lister les noms des fichiers dans le fichier1.txt
chemin_fichier1 = os.path.join(dossier, 'fichier1.txt')
with open(chemin_fichier1, 'w') as f1:
        f1.write("\nListe des fichiers dans le dossier :\n")
        for nom_fichier in fichiers:
            f1.write(f"{nom_fichier}\n")       