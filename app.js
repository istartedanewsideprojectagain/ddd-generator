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

const generateDTO = (basePackage, dtoType, type, fields) => {
    const typeName = type.toLowerCase();
    const variables = {
        basePackage,
        dtoType,
        fields,
        typeName
    }

    const outputFolder = `application/${typeName}/dto`;
    Twig.renderFile(`${boilerPlatePath}/application/dto.java`, variables,(err, output) => {
        if(err) console.error(err)
        writeGeneratedFile(outputFolder, `${dtoType}.java`,output);
    });
}

const generateDTOMapper = (basePackage, dtoType, type, fields) => {
    const typeName = type.toLowerCase();
    const variables = {
        basePackage,
        dtoType,
        dtoMapperType: `${dtoType}Mapper`,
        typeName,
        type,
        fields
    }

    const outputFolder = `application/${typeName}/dto`;
    Twig.renderFile(`${boilerPlatePath}/application/dtoMapper.java`, variables,(err, output) => {
        if(err) console.error(err)
        writeGeneratedFile(outputFolder,`${dtoType}DTOMapper.java`,output);
    });
}

const generateCommand = (basePackage, commandType, type, fields) => {
    const typeName = type.toLowerCase();
    const variables = {
        basePackage,
        commandType,
        typeName,
        fields
    }

    const outputFolder = `application/${typeName}/command`;
    Twig.renderFile(`${boilerPlatePath}/application/command.java`, variables,(err, output) => {
        if(err) console.error(err)
        writeGeneratedFile(outputFolder, `${commandType}.java`,output);
    });
}

const generateCommandMapper = (basePackage, commandType, type, fields) => {
    const typeName = type.toLowerCase();
    const variables = {
        basePackage,
        commandType,
        commandMapperType: `${commandType}Mapper`,
        typeName,
        type,
        fields
    }

    const outputFolder = `application/${typeName}/command`;
    Twig.renderFile(`${boilerPlatePath}/application/commandMapper.java`, variables,(err, output) => {
        if(err) console.error(err)
        writeGeneratedFile(outputFolder,`${commandType}Mapper.java`,output);
    });
}

const generateEndpoint = (basePackage,type,endpoints) => {
    const typeName = type.toLowerCase();
    const typeNamePlural = pluralize(typeName);
    const variables = {
        basePackage,
        type,
        typeName,
        endpoints,
        typeNamePlural,
    }

    const outputFolder = `ui/rest/${typeName}`;
    Twig.renderFile(`${boilerPlatePath}/ui/rest/endpoint.java`, variables,(err, output) => {
        if(err) console.error(err)
        writeGeneratedFile(outputFolder,`${type}Endpoint.java`,output);
    });

}
const generatePOJO = (basePackage,name,additionalData,filePath) => {
    const variables = {
        basePackage: basePackage,
        item: name.toLowerCase(),
        name: name,
        ...additionalData
    }

    let pathArray = filePath.split('/');
    const folder = pathArray.splice(0, 1) + "/" +name.toLowerCase();


    let filename = pathArray[0].charAt(0).toUpperCase() + pathArray[0].slice(1);
    const fileType = filename.split('.')[0]; //Remove the file extention
    if(variables.commandName !== undefined){
        filename = name + variables.commandName.charAt(0).toUpperCase() + variables.commandName.slice(1) + filename
    } else if(variables.dtoName !== undefined){
        filename = name + variables.dtoName.charAt(0).toUpperCase() + variables.dtoName.slice(1) + filename
    } else if(fileType === 'Entity'){
        filename = filename;
    } else if(fileType === 'Command' && variables.commandName === undefined){
        filename = name + variables.name +filename;
    }else if(fileType === 'Dto' && variables.dtoName === undefined){
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
    spec.commands.forEach(command => {

        generateCommand(spec.basePackage,`${spec.type}${command.name}Command`, spec.type, command.fields);
        generateCommandMapper(spec.basePackage,`${spec.type}${command.name}Command`, spec.type, command.fields);
    });

};

const generateDtos = (spec) => {

    spec.dtos.forEach(dto => {
        generateDTO(spec.basePackage,`${spec.type}${dto.name}DTO`, spec.type, dto.fields);
        generateDTOMapper(spec.basePackage,`${spec.type}${dto.name}DTO`, spec.type, dto.fields);
    });
}

const main = async () => {




    const spec = require('./spec.json');
    generateCommands(spec);
    generateDtos(spec);


    generatePOJO(spec.basePackage,spec.type,spec.model, `application/facade.java`);



    generatePOJO(spec.basePackage,spec.type,spec.model, `infrastructure/repositoryJPA.java`);
    generatePOJO(spec.basePackage,spec.type,spec.model, `infrastructure/repositoryWrapper.java`);
    generatePOJO(spec.basePackage,spec.type,spec.model,`infrastructure/model.java`);
    generatePOJO(spec.basePackage,spec.type,spec.entity,`infrastructure/mapper.java`);

    generatePOJO(spec.basePackage,spec.type,spec.entity,`domain/entity.java`);
    generatePOJO(spec.basePackage,spec.type,spec.entity,`domain/id.java`);
    generatePOJO(spec.basePackage,spec.type,spec.entity,`domain/repository.java`);

    generateEndpoint(spec.basePackage,spec.type,spec.endpoints);




}

main();
