#include <iostream>
#include <fstream>
#include <vector>
#include <string>

using namespace std;

void openGradeFiles(ifstream* filenameInput, vector<ofstream*>* nameVector);
void readScores(ifstream* scoreInput, vector<int>* scoreVector);
void writeScores(vector<int>* scoreVector, vector<ofstream*>* nameVector);
void closeInputFile(ifstream* inputFile);
void closeGradeFiles(vector<ofstream*>* nameVector);

void openGradeFiles(ifstream* filenameInput, vector<ofstream*>* nameVector) {
    vector<ofstream*>::iterator nameItr;
    string tmpString;
    for (nameItr = nameVector->begin(); nameItr != nameVector->end(); nameItr++) {
        if (!getline(*filenameInput, tmpString)) {
            printf("Error: getline error\n");
            exit(1);
        }
        (*nameItr)->open(tmpString);
    }
}

void readScores(ifstream* scoreInput, vector<int>* scoreVector) {
    string tmpString;
    while (getline(*scoreInput, tmpString)) {
        scoreVector->push_back(stoi(tmpString));
    }
}

void writeScores(vector<int>* scoreVector, vector<ofstream*>* nameVector) {
    vector<int>::iterator scoreItr;
    for (scoreItr = scoreVector->begin(); scoreItr != scoreVector->end(); scoreItr++) {
        switch (*scoreItr) {
        case 90 ... 100:
            *(*nameVector)[0] << *scoreItr << "\n";
        break;
        case 80 ... 89:
            *(*nameVector)[1] << *scoreItr << "\n";
        break;
        case 70 ... 79:
            *(*nameVector)[2] << *scoreItr << "\n";
        break;
        case 60 ... 69:
            *(*nameVector)[3] << *scoreItr << "\n";
        break;
        default:
            *(*nameVector)[4] << *scoreItr << "\n";
        break;
        }
    }
}

void closeInputFile(ifstream* inputFile) {
    inputFile->close();
}

void closeGradeFiles(vector<ofstream*>* nameVector) {
    vector<ofstream*>::iterator nameItr;
    for (nameItr = nameVector->begin(); nameItr != nameVector->end(); nameItr++) {
        (*nameItr)->close();
    }
}

int main() {
    ifstream* tmpfilenameInput = new ifstream("filename.txt");
    ifstream* tmpscoreInput = new ifstream("score.txt");
    vector<ofstream*>* tmpnameVector = new vector<ofstream*>(5);
    (*tmpnameVector)[0] = new ofstream();
    (*tmpnameVector)[1] = new ofstream();
    (*tmpnameVector)[2] = new ofstream();
    (*tmpnameVector)[3] = new ofstream();
    (*tmpnameVector)[4] = new ofstream();
    vector<int>* tmpscoreVector = new vector<int>();
    
    openGradeFiles(tmpfilenameInput, tmpnameVector);
    readScores(tmpscoreInput, tmpscoreVector);
    writeScores(tmpscoreVector, tmpnameVector);
    closeInputFile(tmpfilenameInput);
    closeInputFile(tmpscoreInput);
    closeGradeFiles(tmpnameVector);

    delete tmpfilenameInput;
    delete tmpscoreInput;
    delete tmpnameVector;
    delete tmpscoreVector;

    return 0;
}
