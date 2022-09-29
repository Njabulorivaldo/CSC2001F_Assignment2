# binary search program makefile
# Njabulo Mdluli
# 20 March 2022

.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
JAVAC=/usr/bin/javac
JAVA=/usr/bin/java


$(BINDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<


CLASSES=BinaryTreeNode.class \
	BTQueueNode.class \
	BTQueue.class \
	BinaryTree.class \
	AVLTree.class AVLTreeTest.class \
	Vaccine.class \
	AVLExperiment.class\

CLASSES_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASSES_FILES)
	
run: $(CLASSES_FILES)
	$(JAVA) -cp $(BINDIR) AVLExperiment
		
clean: 
	rm $(BINDIR)/*.class
	
test:
	python3 scripts/Test.py
	
docs:
	javadoc -d doc/ src/*.java
