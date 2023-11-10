def compterMots(chaine):
    # Divise la chaîne en mots
    mots = chaine.split()

    # Initialise un dictionnaire pour stocker la fréquence des mots
    frequence_mots = {}

    # Compte la fréquence de chaque mot
    for mot in mots:
        # Supprime la ponctuation éventuelle pour éviter les doublons
        mot = mot.strip('.,!?()[]{}"\'')
        # Convertit le mot en minuscules pour éviter la distinction entre majuscules et minuscules
        mot = mot.lower()

        # Incrémente la fréquence du mot dans le dictionnaire
        frequence_mots[mot] = frequence_mots.get(mot, 0) + 1

    return frequence_mots

# Exemple d'utilisation de la fonction
chaine_test = "Ceci est un exemple. un Exemple d'une fonction pour compter les mots dans une chaîne."
resultat = compterMots(chaine_test)
print(resultat)