
CREATE TABLE Client (
    idClient INT PRIMARY KEY,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    date_naiss DATE,
    email VARCHAR(100)
);

CREATE TABLE Fournisseur (
    idFour INT PRIMARY KEY,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    tel VARCHAR(20)
);
CREATE TABLE Commande (
    idCMD INT PRIMARY KEY,
    dateCMD DATE,
    idClient INT,
    FOREIGN KEY (idClient) REFERENCES Client(idClient)
);

CREATE TABLE Produit (
    idProduit INT PRIMARY KEY,
    Designation VARCHAR(255),
    prix DECIMAL(10, 2),
    QteStock INT,
    email VARCHAR(100),
    idFour INT,
    FOREIGN KEY (idFour) REFERENCES Fournisseur(idFour)
);

CREATE TABLE LigneCMD (
    idLigneCMD INT PRIMARY KEY,
    Qte INT,
    idProduit INT,
    idCMD INT,
    FOREIGN KEY (idProduit) REFERENCES Produit(idProduit),
    FOREIGN KEY (idCMD) REFERENCES Commande(idCMD)
);

