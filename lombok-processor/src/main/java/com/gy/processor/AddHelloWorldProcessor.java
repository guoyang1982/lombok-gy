package com.gy.processor;

/**
 * @author guoyang
 * @date 2022/11/1 11:36 上午
 */
import com.google.auto.service.AutoService;
import com.gy.annotation.AddHelloWorld;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes("com.gy.annotation.AddHelloWorld")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class AddHelloWorldProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        final Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        final JavacElements elementUtils = (JavacElements) processingEnv.getElementUtils();
        final TreeMaker treeMaker = TreeMaker.instance(context);
        Set<? extends Element> elements = roundEnv.getRootElements();
        Set<? extends Element> elementsHello = roundEnv.getElementsAnnotatedWith(AddHelloWorld.class);

        elementsHello.forEach(element -> {
            // 根据元素获取对应的语法树 JCTree
            JCTree jcTree = elementUtils.getTree(element);
            jcTree.accept(new TreeTranslator(){
            public void visitMethodDef(JCTree.JCMethodDecl jcMethodDecl) {
                treeMaker.pos = jcMethodDecl.pos;
                jcMethodDecl.body = treeMaker.Block(0, List.of(
                    treeMaker.Exec(
                        treeMaker.Apply(
                            List.<JCTree.JCExpression>nil(),
                            treeMaker.Select(
                                treeMaker.Select(
                                    treeMaker.Ident(
                                        elementUtils.getName("System")
                                    ),
                                    elementUtils.getName("out")
                                ),
                                elementUtils.getName("println")
                            ),
                            List.<JCTree.JCExpression>of(
                                treeMaker.Literal("Hello, world!!!")
                            )
                        )
                    ),
                    jcMethodDecl.body,treeMaker.Exec(
                        treeMaker.Apply(
                            List.<JCTree.JCExpression>nil(),
                            treeMaker.Select(
                                treeMaker.Select(
                                    treeMaker.Ident(
                                        elementUtils.getName("System")
                                    ),
                                    elementUtils.getName("out")
                                ),
                                elementUtils.getName("println")
                            ),
                            List.<JCTree.JCExpression>of(
                                treeMaker.Literal("Hello, world gy11!!!")
                            )
                        )
                    )
                ));
                super.visitMethodDef( jcMethodDecl );
            }
            });
        });

//        for (Element element : roundEnv.getElementsAnnotatedWith(AddHelloWorld.class)) {
//            JCTree.JCMethodDecl jcMethodDecl = (JCTree.JCMethodDecl) elementUtils.getTree(element);
//
//            treeMaker.pos = jcMethodDecl.pos;
//            jcMethodDecl.body = treeMaker.Block(0, List.of(
//                treeMaker.Exec(
//                    treeMaker.Apply(
//                        List.<JCTree.JCExpression>nil(),
//                        treeMaker.Select(
//                            treeMaker.Select(
//                                treeMaker.Ident(
//                                    elementUtils.getName("System")
//                                ),
//                                elementUtils.getName("out")
//                            ),
//                            elementUtils.getName("println")
//                        ),
//                        List.<JCTree.JCExpression>of(
//                            treeMaker.Literal("Hello, world!!!")
//                        )
//                    )
//                ),
//                jcMethodDecl.body,treeMaker.Exec(
//                    treeMaker.Apply(
//                        List.<JCTree.JCExpression>nil(),
//                        treeMaker.Select(
//                            treeMaker.Select(
//                                treeMaker.Ident(
//                                    elementUtils.getName("System")
//                                ),
//                                elementUtils.getName("out")
//                            ),
//                            elementUtils.getName("println")
//                        ),
//                        List.<JCTree.JCExpression>of(
//                            treeMaker.Literal("Hello, world gy!!!")
//                        )
//                    )
//                )
//            ));
//        }
        return false;
    }
}