const fs = require('fs');
const fse = require('fs-extra');
const Twig =  require('twig');
const pluralize = require('pluralize')

const boilerPlatePath = `./boilerplate`;
const outputDir = `./dist`;

  
const writeGeneratedFile = (filePath,fileName,content) => {
    fs.mkdirSync(`${outputDir}/${filePath}`,{ recursive: true });
    fs.writeFileSync(`${outputDir}/${filePath}/${fileName}`, content, 'utf8');
}


const generateContent = (spec,spec2,templatePath) => {
    let generatedContentList = [];
    spec2.forEach(e => {
        generatedContentList.push(generatePOJO(spec.basePackage,spec.itemName, e,templatePath));
    });
    return generatedContentList;
}


const generatePOJO = (basePackage,name,additionnalData,filePath) => {
    const variables = {
        basePackage: basePackage,
        item: name.toLowerCase(),
        name: name,
        ...additionnalData
    }

    let pathArray = filePath.split('/');
    const folder = pathArray.splice(0, 1); 
    

    let filename = pathArray[0].charAt(0).toUpperCase() + pathArray[0].slice(1);
    const fileType = filename.split('.')[0]; //Remove the file extention
    if(variables.commandName !== undefined){
        filename = name + variables.commandName.charAt(0).toUpperCase() + variables.commandName.slice(1) + filename
    } else if(variables.dtoName !== undefined){
        filename = name + variables.dtoName.charAt(0).toUpperCase() + variables.dtoName.slice(1) + filename
    } else if(fileType === 'Entity'){
        filename = filename;
    } if(fileType === 'Command'){
        filename = name + variables.name +filename;
    }
     if(fileType === 'Dto'){
        filename = name + variables.name +filename;
    } else {
        filename = name + filename;
    }
    Twig.renderFile(`${boilerPlatePath}/${filePath}`, variables,(err, output) => {
        if(err) console.error(err)
        //console.log(output);
       
        writeGeneratedFile(folder,filename,output);
    });
}


const generateCommands = (spec) => {
    generateContent(spec,spec.commands,`application/command.java`);

    spec.commands.forEach(command => {
        generatePOJO(spec.basePackage,spec.itemName, { commandName: command.name }, `application/commandMapper.java`);
    });

};

const generateDtos = (spec) => {
    generateContent(spec,spec.dtos, `application/dto.java`);

    spec.dtos.forEach(dto => {
        generatePOJO(spec.basePackage,spec.itemName, { dtoName: dto.name }, `application/dtoMapper.java`);
    });
}


const main = async () => {

    const spec = require('./spec.json');
    console.log(spec)

    generatePOJO(spec.basePackage,spec.itemName,spec.model, `application/facade.java`);

    generateCommands(spec);
    generateDtos(spec);



    generatePOJO(spec.basePackage,spec.itemName,spec.model, `infrastructure/repositoryJPA.java`);
    generatePOJO(spec.basePackage,spec.itemName,spec.model, `infrastructure/repositoryWrapper.java`);
    generatePOJO(spec.basePackage,spec.itemName,spec.model,`infrastructure/model.java`);
    generatePOJO(spec.basePackage,spec.itemName,spec.entity,`infrastructure/mapper.java`);

    generatePOJO(spec.basePackage,spec.itemName,spec.entity,`domain/entity.java`);
    generatePOJO(spec.basePackage,spec.itemName,spec.entity,`domain/id.java`);
    generatePOJO(spec.basePackage,spec.itemName,spec.entity,`domain/repository.java`);





}

main();