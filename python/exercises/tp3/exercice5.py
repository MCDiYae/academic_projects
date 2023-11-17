chaine = 'asdq qwerty zxcv hjkl vvws ppvdf'

# Création du dictionnaire des fréquences
dic_ch = {}
for character in set(chaine):
    dic_ch[character] = chaine.count(character)

# Création de la liste correspondante
liste = []
for character, nbrApr in dic_ch.items():
    liste.append((character, nbrApr))
print(liste)    

# Tri de la liste par ordre décroissant de fréquences
liste.sort(key=lambda x: x[1], reverse=True)

# Affichage des résultats
print("Liste triee par ordre decroissant de frequences:", liste)



#prof
chain = "bonjour les etudiants de iwa"


def getNbrApp(elt):
    return elt[2]


list_caracteres = set(chain)
dict_caractere = {}
for caractere in list_caracteres:
    dict_caractere[caractere] = chain.count(caractere)
liste = []
for caractere, nbrApri in dict_caractere.items():
    liste.append((caractere, nbrApri,nbrApri))

liste.sort(key=getNbrApp, reverse=True)

print('voici ',liste)
