import os
from datetime import datetime

file_path = "D:/iwa project/python/handling_files/exercice_fi/fichier1.txt"


    # Obtenir la date de modification du fichier
modification_time = os.path.getmtime(file_path)

    # Convertir le timestamp en format de date et heure lisible
formatted_time = datetime.fromtimestamp(modification_time)

    # Afficher la date de modification
print(f"Date de modification du fichier {file_path}: {formatted_time}")