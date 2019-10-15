##before Execution Execute these three statements
##export STANFORDTOOLSDIR=$HOME
##export CLASSPATH=$STANFORDTOOLSDIR/stanford-postagger-full-2015-04-20/stanford-postagger.jar:$STANFORDTOOLSDIR/stanford-ner-2015-04-20/stanford-ner.jar:$STANFORDTOOLSDIR/stanford-parser-full-2015-04-20/stanford-parser.jar:$STANFORDTOOLSDIR/stanford-parser-full-2015-04-20/stanford-parser-3.5.2-models.jar
##export STANFORD_MODELS=$STANFORDTOOLSDIR/stanford-postagger-full-2015-04-20/models:$STANFORDTOOLSDIR/stanford-ner-2015-04-20/classifiers

from nltk.tag.stanford import StanfordPOSTagger
from nltk.parse.stanford import StanfordParser
from nltk.corpus import stopwords


print("Sentence segmentation")
tokens="this is pune.Pune is a great city"
tokens=tokens.split(".")
print(tokens)



print("\nTokenizer:")
tokens="this is pune"
tokens=tokens.split(" ")
print(tokens)


print("\nStop Words Removal:")
stop_words = set(stopwords.words('english'))
filtered_words = [w for w in tokens if not w in stop_words]
print(filtered_words)


st = StanfordPOSTagger('english-bidirectional-distsim.tagger')
print("\nPOS tagging:")
print(st.tag('What is the airspeed of an unladen swallow ?'.split()))


parser=StanfordParser(model_path="edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz")
print("\nSyntax Parser:")
print(list(parser.raw_parse("rahul daksh fire")))
