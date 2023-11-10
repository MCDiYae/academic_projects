import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData.dark(
        useMaterial3: true,
        
      ),
      home: const PortfolioPage(),
    );
  }
}

class PortfolioPage extends StatelessWidget {
  const PortfolioPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.all(24),
      child: Scaffold(
        body: Row(
          children: [
            NavigationRail(
              selectedIndex: 0,
              onDestinationSelected: (int index) {
                // Ajoutez ici la logique pour gérer la sélection
              },
              labelType: NavigationRailLabelType.all,
              destinations: const [
                NavigationRailDestination(
                  icon: Icon(Icons.home),
                  label: Text('Home'),
                ),
                NavigationRailDestination(
                  icon: Icon(Icons.assignment),
                  label: Text('Skills'),
                ),
                NavigationRailDestination(
                  icon: Icon(Icons.work),
                  label: Text('Projects'),
                ),
                NavigationRailDestination(
                  icon: Icon(Icons.settings),
                  label: Text('Services'),
                ),
                NavigationRailDestination(
                  icon: Icon(Icons.mail),
                  label: Text('Contact'),
                ),
              ],
            ),
            const VerticalDivider(
              thickness: 1,
              width: 1,
            ),
            // Ajoutez le contenu de la page ici
          ],
        ),
      ),
    );
  }
}
