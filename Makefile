# Répertoire contenant les sources
SRC_DIR = src/fr/iutfbleau/sae32_2023
# Répertoire contenant les fichiers compilés
OUT_DIR = build

SRCS = \
		$(SRC_DIR)/Cell.java \
		$(SRC_DIR)/CellLabel.java \
		$(SRC_DIR)/IncalculableFormulaException.java \
		$(SRC_DIR)/IncorrectFormulaException.java \
		$(SRC_DIR)/CellOperations.java \
		$(SRC_DIR)/FormulaBarComponentAdapter.java \
		$(SRC_DIR)/Node.java \
		$(SRC_DIR)/OperatorNode.java \
		$(SRC_DIR)/NumberNode.java \
		$(SRC_DIR)/CellNode.java \
		$(SRC_DIR)/AbstractSyntaxTree.java \
		$(SRC_DIR)/CellMouseListener.java \
		$(SRC_DIR)/ExitButtonListener.java \
		$(SRC_DIR)/SheetAccessButtonListener.java \
		$(SRC_DIR)/TabPage.java \
		$(SRC_DIR)/FormulaBarListener.java \
		$(SRC_DIR)/HomePage.java \
		$(SRC_DIR)/Main.java

CLS = \
		$(OUT_DIR)/Cell.class \
		$(OUT_DIR)/CellLabel.class \
		$(OUT_DIR)/IncalculableFormulaException.class \
		$(OUT_DIR)/IncorrectFormulaException.class \
		$(OUT_DIR)/CellOperations.class \
		$(OUT_DIR)/FormulaBarComponentAdapter.class \
		$(OUT_DIR)/Node.class \
		$(OUT_DIR)/OperatorNode.class \
		$(OUT_DIR)/NumberNode.class \
		$(OUT_DIR)/CellNode.class \
		$(OUT_DIR)/AbstractSyntaxTree.class \
		$(OUT_DIR)/CellMouseListener.class \
		$(OUT_DIR)/ExitButtonListener.class \
		$(OUT_DIR)/SheetAccessButtonListener.class \
		$(OUT_DIR)/TabPage.class \
		$(OUT_DIR)/FormulaBarListener.class \
		$(OUT_DIR)/HomePage.class \
		$(OUT_DIR)/Main.class \



.SUFFIXES: .java
# Ajout des règles qui ne créent pas de fichiers portant le même nom qu'elles.
.PHONY: build clean run-stats run-tests
# Règle build qui appelle des règles de compilation pour chaque classe du projet.
build: $(CLS)
# Règle de compilation pour chaque fichier .java en fichier .class.
$(CLS): $(OUT_DIR)/%.class: $(SRC_DIR)/%.java
	javac -encoding utf8 -implicit:none -d $(OUT_DIR)/ -classpath $(SRC_DIR)/ $<
# Règle run qui construit le programme et exécute la classe src.Main.
run: build
	java -classpath $(OUT_DIR): Main
jar: build
	jar cvfe PetitTableur.jar Main -C build .

run-jar: jar
	java -jar PetitTableur.jar

# Règle clean qui supprime l'ensemble des fichiers .class.
clean:
	$(RM) -r $(OUT_DIR)